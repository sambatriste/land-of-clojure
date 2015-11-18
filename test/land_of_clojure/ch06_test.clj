(ns land-of-clojure.ch06-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch06 :refer :all]
            [land-of-clojure.test-util :refer :all]))

(deftest hoge
  (is (= (do-game-read "walk east")
         '(walk 'east))))

(deftest tweak-test
  (is (= (tweak-text "aaa! bbb? ccc. d" true false)
         "Aaa! Bbb? Ccc. D")))

(deftest allowed-command?-test
  (is (= (allowed-command? '(hoge fuga)) false))
  (is (= (allowed-command? '(walk west)) true)))
