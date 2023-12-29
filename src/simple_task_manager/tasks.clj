(ns simple-task-manager.tasks
  (:import (java.time LocalDateTime)))

(defrecord Task [description priority due-date completed])

(defn check-prio [prio]
  (contains? (set [1 2 3 4]) prio))

(defn create-task [desc prio due]
  (if (check-prio prio)
    (->Task desc prio due false)
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

(defn complete-task [tasks id]
  (assoc (dissoc tasks id) id (update (get tasks id) :completed (constantly true))))

(defn delete-task [tasks id]
  (dissoc tasks id))

(defn prettify-task [id task]
  (let [overdue (if (.isAfter (:due-date task) (LocalDateTime/now)) "" "OVERDUE ")]
    (str "(" id ") " overdue "Description: " (:description task) ", P" (:priority task) ", Due: " (:due-date task))))

(defn sort-by-prio [tasks]
  (into (sorted-map-by (fn [k1 k2]
                   (compare (get-in tasks [k1 :priority])
                            (get-in tasks [k2 :priority]))))
                 tasks))

(defn get-tasks [tasks show-complete]
  (println "--- Tasks sorted by Priority ---")
  (if show-complete
    (doseq [[id task] (sort-by-prio tasks)]
      (println (if (:completed task) "[x]" "[ ]") (prettify-task id task)))
    (doseq [[id task] (sort-by-prio tasks)]
      (if (not (:completed task)) (println (prettify-task id task))))))

(defn task-amount [tasks]
  (count tasks))
