(ns simple-task-manager.core
  (:require [simple-task-manager.tasks :as tasks]))

(defn create-command [tasks]
  (print "Enter description: ")
  (flush)
  (let [desc (read-line)]
    (print "Enter priority (1-4): ")
    (flush)
    (let [prio (Integer/parseInt (read-line))]
      (if (tasks/check-prio prio)
        (do
          (println "Created task" desc "with priority" prio)
          (tasks/add-task tasks (tasks/create-task desc prio)))
        (do
          (println "Priority must be within 1 and 4")
          tasks)))))

(defn delete-command [tasks]
  (print "Enter task id to delete: ")
  (flush)
  (let [id (Integer/parseInt (read-line))]
    (if (tasks/task-exists tasks id)
      (do
        (println "Deleted task with id" id)
        (tasks/delete-task tasks id))
      (do
        (println "Task with id" id "does not exist")
        tasks))))

(defn list-command [tasks]
  (if (= 0 (tasks/task-amount tasks))
    (println "No tasks created yet.")
    (tasks/get-tasks tasks))
  tasks)

(defn command-input [tasks]
  (println "Available commands: create, delete, list, quit")
  (let [cmd (read-line)]
    (case cmd
      "create" (create-command tasks)
      "delete" (delete-command tasks)
      "list" (list-command tasks)
      "quit" (println "Bye!")
      (do
        (println "Wrong command, try again!")
        (recur tasks)))))

(defn -main []
  (loop [tasks {}]
    (recur (command-input tasks))))
