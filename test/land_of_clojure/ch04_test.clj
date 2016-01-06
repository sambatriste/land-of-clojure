(ns land-of-clojure.ch04-test
  (:require [clojure.test :refer :all]))

(deftest empty-equals-false-NOT
  "Clojureでは、空のリストは偽と等価でない"
  ;; () is not the same as nil
  (is (= (if '() 't 'f) 't)))

(defn my-length [list]
  (if (not (empty? list))
    (inc (my-length (rest list)))
    0))

(deftest my-length-test
  (is (= (my-length '(list with symbols))
         3)))


(deftest if-test
  (is (= (if (odd? 5)
           'odd-number
            ;; 評価されない
           (/ 1 0))
         'odd-number)))
