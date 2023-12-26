(ns simple-task-manager.core
  (:require [simple-task-manager.tasks :as tasks]))

(defn -main []
  (let [task-1 (tasks/create-task "Test" 2)
        task-2 (tasks/create-task "Test2" 1)
        task-3 (tasks/create-task "Test3" 2)
        all-tasks (-> {}
                      (tasks/add-task task-1)
                      (tasks/add-task task-2)
                      (tasks/add-task task-3)
                      (tasks/delete-task 1))]
    (tasks/get-tasks all-tasks)))
