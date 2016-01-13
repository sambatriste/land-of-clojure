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


(deftest dot-label-test
  (is (= (dot-label "abc") "abc"))
  (is (= (dot-label "0123456789012345678901234567890123456789")
         "012345678901234567890123456..."))
  (is (= (dot-label 'abc) "abc"))
  (is (nil? (dot-label nil))))


(deftest nodes->dot-test
  (is (= (nodes->dot land-of-clojure.ch05/*nodes*))
      "aaa")
  )
