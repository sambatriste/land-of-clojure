(ns land-of-clojure.ch06-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch06 :refer :all]
            [land-of-clojure.test-util :refer :all]))

(deftest hoge
  (is (= (do-game-read "walk east")
         '(walk 'east))))

(deftest fuga
  (is (= (tweak-text "car. pen." true false)
         "Car. Pen.")
      ))
