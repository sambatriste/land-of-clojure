(ns land-of-clojure.test-util
  (:require [clojure.string :refer [join]]
            [land-of-clojure.misc :refer :all]
            [clojure.test :refer :all]))


(defn equiv? [actual-as-symbol-list expected-as-string]
  (= (stringify actual-as-symbol-list) expected-as-string))

(deftest stringify-test
  (is (= (stringify '[[a aa bb] [cc 1]]) "a aa bb cc 1")))

