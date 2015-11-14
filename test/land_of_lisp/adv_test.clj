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