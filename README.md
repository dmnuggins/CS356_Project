Project Description



The overall aim of this project is to create a useful meeting scheduling platform that will enable employees to quickly, easily, and effectively schedule meetings. Any employee will be able to schedule a meeting by selecting the employees they wish to invite through a user-friendly GUI interface. A time slot during which all employees are available will then be selected by the user, taking into consideration other meetings the employees are attending and the times they are available (thereby preventing employees from being double-booked in multiple meetings). Finally, any available meeting room that is large enough to accommodate all the invited employees at the time specified will be chosen by the user; if there is no room available for the given time and employees, then the program will inform the user of this exception.

The employee who invited the others is considered the owner of the meeting and given the power to modify the attendees, the reserved room, and the scheduled time, as well as the ability to cancel the meeting. Any update to the meeting will result in notifications (about the changes) to all of the invited employees. Each employee will be able to see what meetings they have on any given day by viewing a calendar that displays meetings they are invited to or hosting.

Employees can update their schedules by adding/deleting/changing the contents of their time slots and selecting visible/not visible to other employees.

Special administrative users can create employee and room records. Rooms are fully described by the administrator, while employee records are created with only a username and password. Regular employees will be able to log in, change the password, and fill in their own details including the hours of the day they are available for meetings. Administrators will have the ability to modify or delete rooms, as well as delete users or reset their password.

The effects of this software will be to increase the overall efficiency of scheduling meetings among employees. Meeting schedulers will know immediately which employees will and will not be able to attend their meetings and act accordingly. Employees will know what meetings they are to attend, when the meeting will be held, and in what room it will be held. This will make more efficient use of the time of everyone involved, resulting in the company saving a substantial amount of money.



Extra Notes:
Long term project
First Submission:
UML Use Case Diagram
Other:
Class Diagrams
Pseudocode for each method
Actual code
GUI...


Additional Program Details:

Choice of employee login or admin login
Administrator login for employee
Admin can be a separate login
Data
  - All is shared...
Login
  - Admin - present admin/employee menu choice
  - Employee - present only employee menu
  - Display notification (separate windows)
    - reminding of meetings/schedule today and tomorrow
    - Message displaying those who had already declined meeting invitations tied to the user’s account
    - Meetings that have been canceled (might be omitted)
    - List of new meetings you’re invited to
Meeting
  - Options
  - Accept/Decline
  - Only Accept
Updating
  - Window does not close until user chooses ACCEPT or DECLINE
  - Can remove Decline options
  - Will need to access all employee schedules (stored in a table)
  - Created meeting methods access separate methods of database class
Owner of meeting
  - modify/cancel meeting...
  - Need to check for current employee availability
  - System should prompt user for potential time conflicts

Admin menu
  - Add employee
  - Change/reset password
  - Create username
  - add/delete employee

Employee menu
  - Schedule
  - Meetings
  - Notifications
Meetings UI
  - Separated by departments
  - List of rooms
  - Time slots rooms are available

DATABASE
All methods needed to access database, separate in a separate class
Main program will call the methods of database classes
Not necessary to fully implement
