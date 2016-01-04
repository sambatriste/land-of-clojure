(ns land-of-clojure.ch02)

;; clojure�ǤϺ��������Ǥ��ʤ��Τ�atom����Ѥ���
(def small (atom 1))
(def big (atom 100))

(defn guess-my-number []
  ;; atom���ͤˤ�@�ǥ��������Ǥ��롣 (deref small)��Ʊ����
  ;; ash�ؿ����Τ�Τ�clojure�ˤʤ��Τǡ�quot�����ؤ��롣
  (quot (+ @small @big) 2))

(defn smaller []
  ;; reset!���ͤ��˲�Ū���ѹ�����
  (reset! big (dec (guess-my-number)))
  (guess-my-number))

(defn bigger []
  ;; reset!���ͤ��˲�Ū���ѹ�����
  (reset! small (inc (guess-my-number)))
  (guess-my-number))

(defn start-over []
  "����ͤ����ꤹ��"
  (reset! small (atom 1))
  (reset! big (atom 100))
  (guess-my-number))
