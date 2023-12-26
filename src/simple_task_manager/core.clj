(ns simple-task-manager.core
  (:require [simple-task-manager.tasks :as tasks]))

(def tasks-state (atom {}))

(defn -main []
  (loop []
    (println "Available commands: create, delete, list")
    (let [cmd (read-line)]
      (case cmd
        "create" (do
                   (print "Enter description: ")
                   (let [desc (read-line)]
                     (print "Enter priority (1-4): ")
                     (let [prio (read-line)]
                       (swap! tasks-state tasks/add-task (tasks/create-task desc prio))))
                   (recur))
        "delete" (do
                   (print "Enter task id to delete: ")
                   (let [id (read-line)]
                     (swap! tasks-state tasks/delete-task id))
                   (recur))
        "list" (do
                 (tasks/get-tasks @tasks-state)
                 (recur))
        "quit" (println "Bye!")
        (do
          (println "Wrong command, try again!")
          (recur))
        ))))
