(ns simple-task-manager.core
  (:require [simple-task-manager.tasks :as tasks])
  (:import (java.time LocalDateTime)
           (java.time.format DateTimeFormatter)))

(defn create-command [tasks]
  (print "Enter description: ")
  (flush)
  (let [desc (read-line)]
    (print "Enter priority (1-4): ")
    (flush)
    (let [prio (Integer/parseInt (read-line))]
      (if (tasks/check-prio prio)
        (do
          (print "Enter due date (example: 2011-12-03T10:15): ")
          (flush)
          (let [due (LocalDateTime/parse (read-line))]
          (println "Created task" desc "with priority" prio "and due date" (.format due (DateTimeFormatter/ISO_DATE_TIME)))
          (tasks/add-task tasks (tasks/create-task desc prio due))))
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
    (let [output (command-input tasks)]
      (if (not(= output nil)) (recur output)))))
