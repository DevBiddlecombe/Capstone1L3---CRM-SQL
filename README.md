# Capstone1L3---CRM-SQL

This is building on from the basic Python CRM management tool.

Focusing on OOP I have integrated the program to manage data via MySql

The program will now store multiple projects and client data on a local MySql server 

Open projects can be editied via the program

All new projects will follow the same naming mechanism to avoid jumbled data

Defensive programming has been used to avoid invalid inputs such as strings where a int / double is required

Automatically generates an invoice for a client based on fees outstanding and fees paid in the beginning as a deposit

Technical Breakdown

- Reads and writes data about projects associated via SQL
- Captures new clients and writes to SQL
- Updates existing projects via SQL
- Finalizes a project - Invoice generated with client details and any outstanding amounts that need to 
be paid. 
- Once the project is finalized the current date is added. This is a function and will avoid user error
- Find all projects that still need to be completed in the database
- Find all projects that are past their due date
- Find a project based on project number or name
- Exception handling using try catch
