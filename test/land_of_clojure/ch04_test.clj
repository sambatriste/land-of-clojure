(ns land-of-clojure.ch04-test
  (:require [clojure.test :refer :all]))

(deftest empty-equals-false-NOT
  "Clojure�Ǥϡ����Υꥹ�Ȥϵ��������Ǥʤ�"
  ;; () is not the same as nil
  (is (= (if '() 't 'f) 't)))

(defn my-length [list]
  (if (not (empty? list))
    (inc (my-length (rest list)))
    0))

(deftest my-length-test
  (is (= (my-length '(list with symbols))
         3)))


(deftest if-test
  (is (= (if (odd? 5)
           'odd-number
            ;; ɾ������ʤ�
           (/ 1 0))
         'odd-number)))
