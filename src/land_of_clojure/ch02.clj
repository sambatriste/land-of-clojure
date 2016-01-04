(ns land-of-clojure.ch02)

;; clojureでは再代入ができないのでatomを使用する
(def small (atom 1))
(def big (atom 100))

(defn guess-my-number []
  ;; atomの値には@でアクセスできる。 (deref small)と同じ。
  ;; ash関数そのものはclojureにないので、quotで代替する。
  (quot (+ @small @big) 2))

(defn smaller []
  ;; reset!で値を破壊的に変更する
  (reset! big (dec (guess-my-number)))
  (guess-my-number))

(defn bigger []
  ;; reset!で値を破壊的に変更する
  (reset! small (inc (guess-my-number)))
  (guess-my-number))

(defn start-over []
  "初期値を設定する"
  (reset! small (atom 1))
  (reset! big (atom 100))
  (guess-my-number))
