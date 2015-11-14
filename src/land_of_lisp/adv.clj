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
(defn- conc [lst] (clojure.string/join " " lst))

(defn- stringify [lst] (conc (map name lst)))

(defn describe-location
  [loc nodes]
  (stringify (get nodes loc)))

(defn describe-path
  [edge]
  (stringify `(there is a ~(nth edge 2) going ~(nth edge 1) from here.)))


(defn describe-paths
  [location edges]
  (conc (map describe-path (get edges location))))


(defn objects-at [loc objs obj-locs]
  (letfn [(at-loc? [obj] (= (get obj-locs obj) loc))]
    (filter at-loc? objs)))

(defn describe-objects [loc objs obj-loc]
  (letfn [(describe-obj [obj] (stringify `(you see a ~obj on the floor.)))]
    (conc (map describe-obj (objects-at loc objs obj-loc)))))
