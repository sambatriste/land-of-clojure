(ns land-of-clojure.ch06
  (:require [clojure.string :refer [upper-case lower-case]]
            [land-of-clojure.ch05 :refer :all]
            ))

(defn exists? [e col]
  (not (nil? (find-first #(= % e) col))))

(defn do-game-read [raw-cmd]
  (let [cmd (read-string (str "(" raw-cmd ")"))]
    (letfn [(quote-it [x] (list 'quote x))]
      (cons (first cmd) (map quote-it (rest cmd))))))

(defn game-read [] (do-game-read (read-line)))

(def ^:dynamic *allowed-commands* '(look walk pickup inventory))

(defn allowed-command? [sexp]
  (let [cmd (first sexp)]
    (exists? cmd *allowed-commands*)))

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
          rest-of-chars (rest chars)]
      (cond
        ;; スペース
        (= chr \space) (str chr (tweak-text rest-of-chars capitalize? literal?))
        ;; ! ? .
        (end-of-sentence? chr) (str chr (tweak-text rest-of-chars true literal?))
        ;; "
        (= chr \") (tweak-text rest-of-chars capitalize? (not literal?))
        ;; リテラル
        literal? (str chr (tweak-text rest false literal?))
        ;; 大文字
        capitalize? (str (upper-case chr) (tweak-text rest-of-chars false literal?))
        ;; その他
        :else (str (lower-case chr) (tweak-text rest-of-chars false false))))))