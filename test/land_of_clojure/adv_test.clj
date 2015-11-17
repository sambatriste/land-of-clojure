(ns land-of-clojure.adv_test
  (:require [clojure.test :refer :all]
            [land-of-clojure.adv :refer :all]
            [clojure.string :refer [join]])
  (:import (clojure.lang Named)))


(defn- simplify [e]
  "シンボルを名前に変換する。hoge/fuga -> 'fuga'"
  (if (instance? Named e) (name e) e))

(defn- stringify [col]
  "シンボルのリストを文字列にする"
  (join " " (map simplify (flatten col))))

(deftest describe-location-test
  (is (= (stringify (describe-location 'attic *nodes*))
         "you are in the attic. there is a giant welding torch in the corner.")))


(deftest describe-path-test
  (is (= (stringify (describe-path '[garden west door]))
         "there is a door going west from here.")))

(deftest describe-paths-test
  (let [actual (describe-paths 'living-room *edges*)]
    (is (= (stringify (first actual))
           "there is a door going west from here."))
    (is (= (stringify (nth actual 1))
           "there is a ladder going upstair from here."))))

(deftest objects-at-test
  (is (= (objects-at 'living-room *objects* *object-locations*)
         '(whiskey bucket))))

(deftest describe-objects-test
  (is (= (stringify (describe-objects 'living-room *objects* *object-locations*))
         "you see a whiskey on the floor. you see a bucket on the floor.")))

(deftest look-test
  (reset! *location* 'living-room)
  (is (= (stringify (look))
         (str "you are in the living room. "
              "a wizard is snoring loudly on the couch. "
              "there is a door going west from here. "
              "there is a ladder going upstair from here. "
              "you see a whiskey on the floor. "
              "you see a bucket on the floor."))))

(deftest walk-test
  (reset! *location* 'living-room)
  (is (= (stringify (walk 'west))
         (str "you are in a beautiful garden. "
              "there is a well in front of you. "
              "there is a door going east from here. "
              "you see a frog on the floor. "
              "you see a chain on the floor."))))

(deftest my-assoc-test
  (is (= (my-assoc 'hoge '((foo (bar 1 2)) (hoge (fuga 1 2 (moge 3 4)))))
         '(fuga 1 2 (moge 3 4)))))

(deftest stringify-test
  (is (= (join " " (map simplify (flatten '[[a aa bb] [cc d]]) ))  "a aa bb cc d"))
  (is (= (stringify '[[a aa bb] [cc 1]]) "a aa bb cc 1" )))
