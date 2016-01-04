(ns land-of-clojure.ch02-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch02 :refer :all]))

;; グローバルな変数が実行時に書き換えられるので、
;; テスト毎にstart-over関数を実行して、値を初期化する。
(use-fixtures :each start-over)

(deftest guess-my-number-test
  (is (= (guess-my-number) 50)))


(deftest guess-my-number-scinario-test
  (is (= (guess-my-number) 50))
  (is (= (bigger) 75))
  (is (= (smaller) 62))
  (is (= (smaller) 56)))

