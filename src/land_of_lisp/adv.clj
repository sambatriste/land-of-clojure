(ns land-of-lisp.adv)



(def ^:dynamic *nodes*
  {:living-room "you are in the living room. a wizard is snoring loudly on the couch."
   :garden      "you are in a beautiful garden. there is a well in front of you."
   :attic       "you are in the attic. there is a giant welding torch in the corner."
   })

(def aaa {:living-room '(you are in the living room. a wizard is snoring loudly on the couch.)
            :garden    '(you are in a beautiful garden. there is a well in front of you.)
            :attic     '(you are in the attic. there is a giant welding torch in the corner.)})

(defn stringify
  [lst]
  (clojure.string/join " " (map name lst)))

(defn describe-loc
  [loc nodes]
  (stringify (get nodes loc)))



(defn describe-location
  [location-to-desc nodes]
  (get nodes location-to-desc))


(def ^:dynamic *edges*
  {:living-room [["garden" "west" "door"]
                 ["attic" "upstair" "ladder"]]
   :garden      [["living-room" "east" "door"]]
   :attic       [["living-room" "downstair" "ladder"]]})


(defn describe-path
  [edge]
  (clojure.string/join
    " "
    ["there is a" (nth edge 2) "going" (nth edge 1) "from here."]))

(defn describe-paths [location edges]
  (clojure.string/join
    " "
    (map describe-path (get edges location))))

