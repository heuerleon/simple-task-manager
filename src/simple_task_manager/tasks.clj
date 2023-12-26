(ns simple-task-manager.tasks)

(defrecord Task [description priority])

(defn create-task [description priority]
  (if (contains? (set [1 2 3 4]) priority)
    (->Task description priority)
    (throw (Throwable. "Priority must be within 1 and 4"))))

(defn new-task-id [tasks]
  (if (empty? tasks)
    0
    (+ 1 (apply max (keys tasks)))))

(defn add-task [tasks new-task]
  (assoc tasks
    (new-task-id tasks)
    new-task))

(defn task-exists [tasks id]
  (contains? tasks id))

(defn delete-task [tasks id]
  (dissoc tasks id))

(defn prettify-task [[id task]]
  (str "(" id ") Description: " (:description task) ", P" (:priority task)))

(defn get-tasks [tasks]
  (doseq [task tasks]
    (println (prettify-task task))))
