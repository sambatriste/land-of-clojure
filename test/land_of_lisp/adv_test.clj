(ns land-of-lisp.adv_test
  (:require [clojure.test :refer :all]
            [land-of-lisp.adv :refer :all]))


(deftest describe-location-test
  (is (= (describe-location 'attic nodes)
         "you are in the attic. there is a giant welding torch in the corner.")))


(deftest describe-path-test
  (is (= (describe-path '[garden west door])
         "there is a door going west from here.")))

(deftest describe-paths-test
  (is (= (describe-paths 'living-room edges)
         "there is a door going west from here. there is a ladder going upstair from here.")))

(deftest objects-at-test
  (is (= (objects-at 'living-room objects object-locations)
         '(whiskey bucket))))

(deftest describe-objects-test
  (is (= (describe-objects 'living-room objects object-locations)
         "you see a whiskey on the floor. you see a bucket on the floor.")))

(deftest look-test
  (set-location 'living-room)
  (is (= (look)
         (str "you are in the living room. "
              "a wizard is snoring loudly on the couch. "
              "there is a door going west from here. "
              "there is a ladder going upstair from here. "
              "you see a whiskey on the floor. "
              "you see a bucket on the floor."))))

(deftest set-location-test
  (set-location 'west)
  (is (= (current-location) 'west)))

(deftest current-edge-test
  (set-location 'living-room)
  (is (= (current-edge) '[[garden west door]
                         [attic upstair ladder]]))
  )
(deftest walk-test
  (set-location 'living-room)
  (is (= (walk 'west)
         (str "you are in a beautiful garden. "
              "there is a well in front of you. "
              "there is a door going east from here. "
              "you see a frog on the floor. "
              "you see a chain on the floor."))))