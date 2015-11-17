(ns land-of-clojure.adv-test
  (:require [clojure.test :refer :all]
            [land-of-clojure.adv :refer :all]
            [land-of-clojure.test-util :refer :all]))



(defn my-fixture [f]
  (letfn [(init []
            "プレイヤーの場所とオブジェクトの配置を初期化する。"
            (reset! *object-locations* initial-object-location)
            (reset! *location* initial-location))]
    (init)
    (f)
    (init)))

(use-fixtures :each my-fixture)


(deftest describe-location-test
  (is (equiv? (describe-location 'attic *nodes*)
              "you are in the attic. there is a giant welding torch in the corner.")))


(deftest describe-path-test
  (is (equiv? (describe-path '[garden west door])
              "there is a door going west from here.")))

(deftest describe-paths-test
  (let [actual (describe-paths 'living-room *edges*)]
    (is (equiv? (first actual)
                "there is a door going west from here."))
    (is (equiv? (nth actual 1)
                "there is a ladder going upstair from here."))))

(deftest objects-at-test
  (is (= (objects-at 'living-room *objects* (current-object-locations))
         '(whiskey bucket))))

(deftest describe-objects-test
  (is (equiv? (describe-objects 'living-room *objects* (current-object-locations))
              "you see a whiskey on the floor. you see a bucket on the floor.")))

(deftest look-test
  (is (equiv? (look)
              (str "you are in the living room. "
                   "a wizard is snoring loudly on the couch. "
                   "there is a door going west from here. "
                   "there is a ladder going upstair from here. "
                   "you see a whiskey on the floor. "
                   "you see a bucket on the floor."))))

(deftest walk-test
  (testing "移動する。"
    (is (equiv? (walk 'west)
                (str "you are in a beautiful garden. "
                     "there is a well in front of you. "
                     "there is a door going east from here. "
                     "you see a frog on the floor. "
                     "you see a chain on the floor."))))
  (testing "移動できない。"
    (is (equiv? (walk 'hoge)
                "you cannot ge that way."))))

(deftest pickup-test
  (testing "ものを取る"
    (is (= (stringify (pickup 'bucket))
           "you are now carrying the bucket")))
  (testing "ものが無い"
    (is (= (stringify (pickup 'foo))
           "you cannot get that."))))

(deftest inventory-test
  (testing "何も持っていない"
    (is (= (inventory) '(item-))))
  (testing "ウィスキーを持っている"
    (pickup 'whiskey)
    (is (= (inventory) '(item- whiskey)))))

(deftest my-assoc-test
  (let [alist '((foo (bar 1 2)) (hoge (fuga 1 2 (moge 3 4))))]
    (is (= (my-assoc 'hoge alist) '(fuga 1 2 (moge 3 4))))
    (is (= (my-assoc 'foo alist) '(bar 1 2)))
    (is (nil? (my-assoc nil alist)))))


