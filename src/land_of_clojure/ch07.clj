(ns land-of-clojure.ch07
  (:import (clojure.lang Named)))

(defn dot-name [exp]
  (clojure.string/replace exp #"[^0-9A-Za-z]" "_"))

(def ^:dynamic *max-label-length* 30)

(defn simplify [e]
  "シンボルを名前に変換する。hoge/fuga -> 'fuga'"
  )

(defn dot-label [exp]
  (if exp
    (let [s (cond (instance? Named exp) (name exp) :else exp)]
      (if (> (.length s) *max-label-length*)
        (str (.substring s 0 (- *max-label-length* 3)) "...")
        s))))