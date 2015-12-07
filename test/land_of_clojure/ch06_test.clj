(ns land-of-clojure.ch06-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch06 :refer :all]
            [land-of-clojure.misc :refer :all]
            [land-of-clojure.test-util :refer :all]))

(deftest fuga
  (is (= (do-game-read "walk east")
         '(walk 'east))))

(deftest allowed-command?-test
  (is (true? (allowed-command? '(walk)))))

(deftest delete-chars-test
  (is (= (string-trim [\a \d] "abcd")
         "bc")))

(deftest stringify-test-a
  (is (= (stringify '(not only does this sentence hava a "comma," it also mentions the "iPad."))
         "not only does this sentence hava a \"comma,\" it also mentions the \"iPad.\"")))
(deftest game-print-test
  (is (= (game-print '(" iPad. "))
         " iPad. "))
  (is (= (game-print '(not only does this sentence hava a "comma," it also mentions the "iPad."))
         "Not only does this sentence hava a comma, it also mentions the iPad.")))

(deftest symbolize-test
  (is (= (symbolize "look")
         '(land-of-clojure.ch05/look))))
;(deftest aaa
;  (is (= (game-eval "look") "aaa")) )