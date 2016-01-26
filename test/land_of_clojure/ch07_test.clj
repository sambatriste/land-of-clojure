;; -*- coding: utf-8-unix -*-
(ns land-of-clojure.ch07-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch05 :refer :all]
            [land-of-clojure.ch07 :refer :all]
            [land-of-clojure.misc :refer :all]
            [land-of-clojure.test-util :refer :all]))

(deftest dot-name-test
  "英数字でない文字がアンダースコアに置き換わること"
  (is (= (dot-name "@aZ09!") "_aZ09_"))
  (is (= (dot-name '$ABC!) "_ABC_")))

(deftest chop-test
  (is (= (chop "abc" 30) "abc"))
  (is (= (chop "0123456789012345678901234567890123456789" 30)
         "012345678901234567890123456...")))

(deftest dot-label-test
  (is (= (dot-label 'abc) "abc"))
  (is (= (dot-label '[abc def]) "abc def"))
  (is (nil? (dot-la


(deftest nodes->dot5/*nodes*) nil)))


(deftest edges->dot-test
  (is (= (edges->do
(doseq [edge land-of-clojure.ch05/*edges*]
  (let [from (first edge)
        dests (rest edge)]
    (doseq [dest dests] ;; カッコが１こ余計にあるのではずす
      (doseq [e dest]
        (print from
              label)]
             (pri


