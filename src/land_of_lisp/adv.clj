(ns land-of-lisp.adv)


(def nodes '{living-room (you are in the living room. a wizard is snoring loudly on the couch.)
             garden      (you are in a beautiful garden. there is a well in front of you.)
             attic       (you are in the attic. there is a giant welding torch in the corner.)})

(def edges
  '{living-room [[garden west door]
                 [attic upstair ladder]]
    garden      [[living-room east door]]
    attic       [[living-room downstair ladder]]})

(def objects '[whiskey bucket frog chain])
(def object-locations
  '{whiskey living-room
    bucket  living-room
    chain   garden
    frog    garden})

(def location (atom 'living-room) )

(defn describe-location
  [loc nodes]
  (get nodes loc))

(defn describe-path
  [edge]
  `(there is a ~(nth edge 2) going ~(nth edge 1) from here.))


(defn describe-paths
  [location edges]
  (map describe-path (get edges location)))


(defn objects-at [loc objs obj-locs]
  (letfn [(at-loc? [obj] (= (get obj-locs obj) loc))]
    (filter at-loc? objs)))

(defn describe-objects [loc objs obj-loc]
  (letfn [(describe-obj [obj] `(you see a ~obj on the floor.))]
    (map describe-obj (objects-at loc objs obj-loc))))

(defn get-location []
  (deref location))
(defn look []
  (let [loc (get-location)]
    [(describe-location loc nodes)
     (describe-paths loc edges)
     (describe-objects loc objects object-locations)]))

(defn- find-first [pred col]
  (first (filter pred col)))

(defn set-location [new-location] (reset! location new-location))

(defn edge-of [direction]
  (let [edge (get edges (get-location))]
    (find-first #(= direction (nth % 1)) edge)))

(defn walk [direction]
  (let [next (edge-of direction)]
    (if next
      (do
        (set-location (first next))
        (look))
      '(you cannot ge that way.))))
