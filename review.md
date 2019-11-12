# Code Review Defect List

| Reviewer: [https://github.com/cpbarrett](https://github.com/cpbarrett) | GH Repo: [https://github.com/cpbarrett/cpbarret-SER316](https://github.com/cpbarrett/cpbarret-SER316) |   |
| --- | --- | --- |
|   |   |   |
|   |   |   |
| ID # | Location | Problem Description | Problem |
| File and Line Number | Category | Severity |
| 1 | Student.java Line 1 | Missing class banner comment  | CG 2 | LOW |
| 2 | Course.java Line 32 | GetName() and SetName() should be lower camelCase  | CG 4 | LOW |
| 3 | Course.java Line 49 | calculateAverageWithoutMinWithoutMax() has no method banner  | CG 3 | LOW |
| 4 | Major.java Line 3 | Lazy class: class does too little  | CS 5 | LOW |
| 5 | Student.java Line 29 | Feature Envy: this class includes too methods that should be inside of the Major class  | CS 4 | LOW |
| 6 | Course.java Line 163 | calculateMax() returns -1 if the collection contains only 1 item  | FD | BR |
| 7 | Course.java Line 42 | ArrayList\&lt;Integer\&gt; values = new ArrayList\&lt;Integer\&gt;(points.values()); is duplicated many times throughout the Course.java Class. It would be better to have a single getter method for this statement.  | CS 1 | MJ |
| 8 | Course.java Line 159 | Dead code here. Collection cannot be null as it is not a function parameter. | FD | MJ |
|   |   |    |   |   |
|   |   |    |   |   |
|   |   |    |   |   |
|   |   |    |   |   |
|   |   |    |   |   |
|   |   |    |   |   |

Category:        **CS –** Code Smell defect **. CG –** Violation of a coding guideline. Provide the guideline number. **FD** – Functional defect. Code will not produce the expected result. **MD –** Miscellaneous defect, for all other defects.

Severity:       **BR** - Blocker, must be fixed asap. **MJ** – Major, of high importance but not a Blocker **LOW** – Low.
