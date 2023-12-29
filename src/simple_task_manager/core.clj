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

(defn edit-command [tasks]
  (print "Enter task id to edit: ")
  (flush)
  (let [id (Integer/parseInt (read-line))]
    (if (tasks/task-exists tasks id)
      (do
        (println "Editing task with id" id)
        (print "Enter new description: ")
        (flush)
        (let [desc (read-line)]
          (print "Enter new priority (1-4): ")
          (flush)
          (let [prio (Integer/parseInt (read-line))]
            (if (tasks/check-prio prio)
              (do
                (print "Enter new due date (example: 2011-12-03T10:15): ")
                (flush)
                (let [due (LocalDateTime/parse (read-line))]
                  (println "Set description of" id "to" desc "with priority" prio "and due date" (.format due (DateTimeFormatter/ISO_DATE_TIME)))
                  (tasks/update-task tasks id (tasks/create-task desc prio due))))
              (do
                (println "Priority must be within 1 and 4")
                tasks)))))
      (do
        (println "Task with id" id "does not exist")
        tasks))))

(defn complete-command [tasks]
  (print "Enter task id to complete: ")
  (flush)
  (let [id (Integer/parseInt (read-line))]
    (if (tasks/task-exists tasks id)
      (do
        (println "Completed task with id" id)
        (tasks/complete-task tasks id))
      (do
        (println "Task with id" id "does not exist")
        tasks))))

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
    (do
      (print "Only view incomplete tasks? [Y/n]: ")
      (flush)
      (let [only-incomplete (read-line)]
        (tasks/get-tasks tasks (= only-incomplete "n")))))
  tasks)

(defn command-input [tasks]
  (println "Available commands: create, edit, complete, delete, list, quit")
  (let [cmd (read-line)]
    (case cmd
      "create" (create-command tasks)
      "edit" (edit-command tasks)
      "complete" (complete-command tasks)
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
