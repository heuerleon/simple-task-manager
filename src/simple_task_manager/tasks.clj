(ns simple-task-manager.tasks
  (:import (java.time LocalDateTime)))

(defrecord Task [description priority due-date])

(defn check-prio [prio]
  (contains? (set [1 2 3 4]) prio))

(defn create-task [desc prio due]
  (if (check-prio prio)
    (->Task desc prio due)
    (throw (Throwable. "Priority must be within 1 and 4"))))

(defn new-task-id [tasks]
  (if (empty? tasks)
    0
    (+ 1 (apply max (keys tasks)))))

(defn add-task [tasks new-task]
  (assoc tasks
    (new-task-id tasks)
    new-task))

(defn update-task [tasks id new-task]
  (dissoc tasks id)
  (assoc tasks id new-task))

(defn task-exists [tasks id]
  (contains? tasks id))

(defn delete-task [tasks id]
  (dissoc tasks id))

(defn prettify-task [id task]
  (str "(" id ") Description: " (:description task) ", P" (:priority task) ", due: " (:due-date task)))

(defn sort-by-prio [tasks]
  (into (sorted-map-by (fn [k1 k2]
                   (compare (get-in tasks [k1 :priority])
                            (get-in tasks [k2 :priority]))))
                 tasks))

(defn get-tasks [tasks]
  (println "--- Tasks sorted by Priority ---")
  (doseq [[id task] (sort-by-prio tasks)]
    (if (.isAfter (:due-date task) (LocalDateTime/now))
      (println (prettify-task id task))
      (println "OVERDUE" (prettify-task id task)))))

(defn task-amount [tasks]
  (count tasks))
