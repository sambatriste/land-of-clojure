;; -*- coding: utf-8-unix -*-
(ns land-of-clojure.ch07-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.misc :refer :all]
            [land-of-clojure.test-util :refer :all]))

(deftest simplify-test
  (is (= (simplify 'abc) "abc"))
  (is (= (simplify "def") "def"))
  (is (= (simplify nil) nil)))

(deftest stringify-test
  (is (= (stringify '[abc]) "abc"))
  (is (= (stringify '[abc def]) "abc def")))
