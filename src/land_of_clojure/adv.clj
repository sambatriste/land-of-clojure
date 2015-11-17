(ns land-of-clojure.adv)

(defn- find-first [pred col]
  "条件に合致する最初の要素を取得する。"
  (first (filter pred col)))

(defn my-assoc
  [key alist]
  "Common LISPのassoc代替。alistからkeyに対応するvalueを取得する。"
  (let [first-entry (find-first #(= (first %) key) alist)
        val (rest first-entry)]
    (first val)))

(def ^:dynamic *nodes*
  '((living-room (you are in the living room. a wizard is snoring loudly on the couch.))
     (garden (you are in a beautiful garden. there is a well in front of you.))
     (attic (you are in the attic. there is a giant welding torch in the corner.))))

(def ^:dynamic *edges*
  '((living-room ((garden west door)
                   (attic upstair ladder)))
     (garden ((living-room east door)))
     (attic ((living-room downstair ladder)))))

(def ^:dynamic *objects* '(whiskey bucket frog chain))

(def ^:dynamic *object-locations*
  '((whiskey living-room)
     (bucket living-room)
     (chain garden)
     (frog garden)))

(def ^:dynamic *location* (atom 'living-room))

(defn describe-location
  [loc nodes]
  (my-assoc loc nodes))

(defn describe-path
  [edge]
  `(there is a ~(nth edge 2) going ~(nth edge 1) from here.))

(defn describe-paths
  [location edges]
  (map describe-path (my-assoc location edges)))

(defn objects-at [loc objs obj-locs]
  (letfn [(at-loc? [obj] (= (my-assoc obj obj-locs) loc))]
    (filter at-loc? objs)))

(defn describe-objects [loc objs obj-loc]
  (letfn [(describe-obj [obj] `(you see a ~obj on the floor.))]
    (map describe-obj (objects-at loc objs obj-loc))))


(defn look []
  (let [loc (deref *location*)]
    [(describe-location loc *nodes*)
     (describe-paths loc *edges*)
     (describe-objects loc *objects* *object-locations*)]))

(defn- edge-of [direction]
  "*edges*から現在位置に対応するエントリを取得する。"
  (let [edges-of-current-location (my-assoc (get-location) *edges*)]
    (find-first #(= direction (nth % 1)) edges-of-current-location)))

(defn walk [direction]
  (let [next (edge-of direction)]
    (if next
      (do
        (reset! *location* (first next))
        (look))
      '(you cannot ge that way.))))
