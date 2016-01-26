;; -*- coding: utf-8-unix -*-
(ns land-of-clojure.ch07
  (:require [land-of-clojure.misc :refer :all])
  (:import (clojure.lang Named)))

(defn dot-name [exp]
  (clojure.string/replace exp #"[^0-9A-Za-z]" "_"))

(def ^:dynamic *max-label-length* 30)


(defn dot-label [exp]
  "文字が長過ぎる場合は切り詰める"
  (if exp
    (let [s (stringify exp)]
      (chop s *max-label-length*))))
(defn- chop [s max-length]
  (if (> (.length s) max-length)
    (str (.substring s 0 (- max-length 3)) "...")
    s))


(defn nodes->dot [nodes]
  (doseq [node nodes]
    (println)
    (print (dot-name (first node)))
    (print "[label=\"")
    (print (dot-label node))
    (print "\"];")))


