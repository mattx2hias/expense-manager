# Expense Manager

## Project Description

A REST API that helps manage the process of reimbursing employees and their expenses. Employees can be created, updated,
or deleted. Expenses can be added or updated to a status of approved or denied with a default status of pending. Reviewed expenses cannot be updated.


## HTTP Routes

### Employee Routes
- POST /employees 
  - returns a 201
- GET /employees
- GET /employees/120
  - returns a 404 if employee not found
- PUT /employees/150
  - returns a 404 if employee not found
- DELETE /employees/190
  - returns a 404 if employee not found


### Expenses routes
- POST /expenses 
  - returns a 201
- GET /expenses
- GET /expenses?status=pending
  - also can get status approved or denied
- GET /expenses/12
  - returns a 404 if expense not found
- PUT /expenses/15
  - returns a 404 if expense not found
- PATCH /expenses/20/approve
  - returns a 404 if expense not found
- PATCH /expenses/20/deny
  - returns a 404 if expense not found
- DELETE /expenses/19
  - returns a 404 if car not found


## Getting Started

`git clone https://github.com/qpco/expense-manager`



