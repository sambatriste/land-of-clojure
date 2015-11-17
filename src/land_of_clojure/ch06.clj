(ns land-of-clojure.ch06
  (:require [clojure.string :refer [upper-case lower-case]]))




(defn do-game-read [raw-cmd]
  (let [cmd (read-string (str "(" raw-cmd ")"))]
    (letfn [(quote-it [x] (list 'quote x))]
      (cons (first cmd) (map quote-it (rest cmd))))))

(defn game-read [] (do-game-read (read-line)))

(def ^:dynamic *allowed-commands* '(look walk pickup inventory))

(defn allowed-command? [sexp]
  (contains? *allowed-commands* (first sexp)))
(defn game-eval [sexp]
  (if (allowed-command? sexp)
    (eval sexp)
    '(i do not know that command.)))

(defn end-of-sentence? [chr]
  (case chr
    \! true
    \? true
    \. true
    false)
  )
(defn tweak-text [chars capitalize? literal?]
  (if (not (empty? chars))
    (let [chr (first chars)
          rst (rest chars)]
      (cond (= chr \space) (str chr (tweak-text rst capitalize? literal?))
            (end-of-sentence? chr) (str chr (tweak-text rst true literal?))
            (= chr \") (tweak-text rst capitalize? (not literal?))
            literal? (str chr (tweak-text rest false literal?))
            capitalize? (str (upper-case chr) (tweak-text rst false literal?))
            :else (str (lower-case chr) (tweak-text rst false false))))))