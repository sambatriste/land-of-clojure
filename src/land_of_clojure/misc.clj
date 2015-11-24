(ns land-of-clojure.misc
  (:require [clojure.string :refer [join]])
  (:import (clojure.lang Named)))


(defn simplify [e]
  "シンボルを名前に変換する。hoge/fuga -> 'fuga'"
  (cond (instance? Named e) (name e)
        (instance? java.lang.String e) (str \" e \")
        :else e)

  )

(defn stringify [col]
  "シンボルのリストを文字列にする"
  (join " " (map simplify (flatten col))))
