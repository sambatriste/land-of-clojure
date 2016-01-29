;; -*- coding: utf-8-unix -*-
(ns land-of-clojure.ch07
  (:require [land-of-clojure.misc :refer :all])
  (:import (clojure.lang Named)))

(defn dot-name [exp]
  (clojure.string/replace exp #"[^0-9A-Za-z]" "_"))

(def ^:dynamic *max-label-length* 30)

(defn chop [s max-length]
  (if (> (.length s) max-length)
    (str (.substring s 0 (- max-length 3)) "...")
    s))

(defn dot-label [exp]
  "文字が長過ぎる場合は切り詰める"
  (if exp
    (let [s (stringify exp)]
      (chop s *max-label-length*))))

(defn nodes->dot [nodes]
  (doseq [node nodes]
    (println)
    (print (dot-name (first node)))
    (print "[label=\"")
    (print (dot-label node))
    (print "\"];")))

;; https://clojuredocs.org/clojure.core/with-open
;; https://github.com/clojure-cookbook/clojure-cookbook/blob/master/04_local-io/4-01_writing-to-stdout-and-stderr.asciidoc
;; http://stackoverflow.com/questions/1257028/why-should-i-use-apply-in-clojure
(defn dot->png [fname thunk]
  (with-open [f (clojure.java.io/writer fname)]
    (binding [*out* f]
      (apply thunk)))
  (let [dot-cmd (str "dot -Tpng -O " fname)]
    (clojure.java.shell/sh  dot-cmd)))
