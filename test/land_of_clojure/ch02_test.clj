(ns land-of-clojure.ch02-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch02 :refer :all]))

;; �����Х���ѿ����¹Ի��˽񤭴�������Τǡ�
;; �ƥ������start-over�ؿ���¹Ԥ��ơ��ͤ��������롣
(use-fixtures :each start-over)

(deftest guess-my-number-test
  (is (= (guess-my-number) 50)))


(deftest guess-my-number-scinario-test
  (is (= (guess-my-number) 50))
  (is (= (bigger) 75))
  (is (= (smaller) 62))
  (is (= (smaller) 56)))

