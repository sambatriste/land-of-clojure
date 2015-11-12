(ns land-of-lisp.adv_test
  (:require [clojure.test :refer :all]
            [land-of-lisp.adv :refer :all]))

(deftest aaa-test
  (let [v (get aaa :living-room)]
    (is (= (clojure.string/join " " (map name v))
           "you are in the living room. a wizard is snoring loudly on the couch."))))

(deftest nodes-test
  (is (= (:living-room *nodes*)
         "you are in the living room. a wizard is snoring loudly on the couch.")))

(deftest describe-location-test
  (is (= (describe-location :attic *nodes*)
         "you are in the attic. there is a giant welding torch in the corner.")))



(deftest describe-loc-test
  (is (= (describe-loc :attic aaa)
         "you are in the attic. there is a giant welding torch in the corner.")))



(deftest describe-path-test
  (is (= (describe-path ["garden" "west" "door"])
         "there is a door going west from here.")))

(deftest describe-paths-test
  (is (= (describe-paths :living-room *edges*)
         "there is a door going west from here. there is a ladder going upstair from here.")))