(ns land-of-clojure.ch06
  (:require [clojure.string :refer [upper-case lower-case split]]
            [land-of-clojure.ch05 :refer :all]
            [land-of-clojure.misc :refer :all]))

(def ^:dynamic *allowed-commands* '(look walk pickup inventory))

(defn exists? [e col]
  (not (nil? (find-first #(= % e) col))))


(defn string-trim [chars-to-delete target-string]
  (letfn [(should-delete? [ch] (empty? (filter #(= % ch) chars-to-delete)))]
    (apply str (filter should-delete? target-string)) ))

(defn do-game-read [raw-cmd]
  (let [cmd (read-string (str "(" raw-cmd ")"))]
    (letfn [(quote-it [x] (list 'quote x))]
      (cons (first cmd) (map quote-it (rest cmd))))))

(defn game-read [] (do-game-read (read-line)))

(defn symbolize [command-line]
  (let [cmd (split command-line #" ")]
    (map #(symbol 'land-of-clojure.ch05 %) cmd)))

(defn allowed-command? [sexp]
  (let [cmd (first sexp)]
    (some? (find-first #(= % cmd) *allowed-commands*))))

(defn game-eval [command-line]
  (let [sexp (symbolize command-line)]
    (if (allowed-command? sexp)
      (binding [*ns* 'land-of-clojure.ch05]
        (eval (list sexp)))
      '(i do not know that command.))))

(defn eos? [ch]
  (boolean (re-matches #"\(|!|\?|\." (str ch))))


(defn tweak-text
  [chars capitalize? literal?]
  (if (not (empty? chars))
    (let [ch (first chars)
          rst (rest chars)]
      (cond (= ch \space) (str ch (tweak-text rst capitalize? literal?))
            (eos? ch) (str ch (tweak-text rst true literal?))
            (= ch \") (tweak-text rst capitalize? (not literal?))
            literal? (str ch (tweak-text rst false literal?))
            capitalize? (str (upper-case ch) (tweak-text rst false literal?))
            :else (str (lower-case ch) (tweak-text rst false false))))))

(defn game-print [lst]
  (let [txt (tweak-text (stringify lst) true false)]
    (println txt)
    txt))

(defn game-repl []
  (let [command-line (game-read)]
    (if (not (= (first command-line) 'quit))
      (game-print (game-eval command-line))
      (game-repl))))

(defn -main [& args]
  (println "input command.")
  (game-repl))

