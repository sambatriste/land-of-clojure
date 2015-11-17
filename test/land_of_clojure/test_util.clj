(ns land-of-clojure.test-util
  (:require [clojure.string :refer [join]]
            [clojure.test :refer :all])
  (:import (clojure.lang Named)))


(defn simplify [e]
  "シンボルを名前に変換する。hoge/fuga -> 'fuga'"
  (if (instance? Named e) (name e) e))

(defn stringify [col]
  "シンボルのリストを文字列にする"
  (join " " (map simplify (flatten col))))

(defn equiv? [actual-as-symbol-list expected-as-string]
  (= (stringify actual-as-symbol-list) expected-as-string))

(deftest stringify-test
  (is (= (stringify '[[a aa bb] [cc 1]]) "a aa bb cc 1")))

