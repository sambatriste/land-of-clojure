(ns land-of-clojure.ch02-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch02 :refer :all]))


(deftest guess-my-number-test
  (start-over)
  (is (= (guess-my-number) 50))
  (is (= (bigger) 75))
  (is (= (smaller) 62))
  (is (= (smaller) 56)))

(deftest flet-test
  ;; flet�Τ�����letfn��Ȥ�
  (letfn [(f [n] (+ n 10))]
    (is (= (f 5) 15)))

  (letfn [(f [n] (+ n 10))
          (g [n] (- n 3))]
    (is (= (g (f 5)) 12))))

(deftest labels-test
  ;; labels�Τ�����letfn��Ȥ�
  (letfn [(a [n] (+ n 5))
          (b [n] (+ (a n) 6))]
    (is (= (b 10) 21))))
