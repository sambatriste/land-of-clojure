;; -*- coding: utf-8-unix -*-
(ns land-of-clojure.ch07-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.ch05 :refer :all]
            [land-of-clojure.ch07 :refer :all]
            [land-of-clojure.misc :refer :all]
            [land-of-clojure.test-util :refer :all]))

(deftest dot-name-test
  "英数字でない文字がアンダースコアに置き換わること"
  (is (= (dot-name "@aZ09!") "_aZ09_"))
  (is (= (dot-name '$ABC!) "_ABC_")))

(deftest chop-test
  "指定した文字数以上だと切り詰められること"
  (is (= (chop "abc" 30) "abc"))
  (is (= (chop "0123456789012345678901234567890123456789" 30)
         "012345678901234567890123456...")))

(deftest dot-label-test
  (is (= (dot-label 'abc) "abc"))
  (is (= (dot-label '[abc def]) "abc def"))
  (is (nil? (dot-label))))

(deftest nodes->dot-test
  (is (= (with-out-str (nodes->dot land-of-clojure.ch05/*nodes*))
         (str
          "\n"
          "living_room[label=\"(living-room you are in the ...)\"];\n"
          "garden[label=\"(garden you are in a beautif...)\"];\n"
          "attic[label=\"(attic you are in the attic....)\"];"))))

(deftest edges->dot-test
  (is (= (with-out-str (edges->dot land-of-clojure.ch05/*edges*))
         (str
          "\n"
          "living_room->garden[label=\"(west door)\"]\n"
          "living_room->attic[label=\"(upstair ladder)\"]\n"
          "garden->living-room[label=\"(east door)\"]\n"
          "attic->living-room[label=\"(downstair ladder)\"]\n"
          ))))

(deftest graph->dot-test
  (is (= (with-out-str (graph->dot land-of-clojure.ch05/*nodes*
                                   land-of-clojure.ch05/*edges*))
         (str
          "digraph{\n"
          "living_room[label=\"(living-room you are in the ...)\"];\n"
          "garden[label=\"(garden you are in a beautif...)\"];\n"
          "attic[label=\"(attic you are in the attic....)\"];\n"
          "living_room->garden[label=\"(west door)\"]\n"
          "living_room->attic[label=\"(upstair ladder)\"]\n"
          "garden->living-room[label=\"(east door)\"]\n"
          "attic->living-room[label=\"(downstair ladder)\"]\n"
          "}"
          ))))

(dot->png
 "test.png"
 (fn []
   (graph->dot land-of-clojure.ch05/*nodes*
               land-of-clojure.ch05/*edges*)))


