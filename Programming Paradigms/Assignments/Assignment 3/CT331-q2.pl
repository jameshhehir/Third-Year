takes(tom, ct331).
takes(mary, ct331).
takes(joe, ct331).
takes(tom, ct345).
takes(mary, ct345).
instructs(bob, ct331).
instructs(ann, ct345).

teaches(TEACHER, STUDENT) :- instructs(TEACHER, X),
						takes(STUDENT, X).

classmates(STUDENT1, STUDENT2) :- takes(STUDENT1, X),
					  takes(STUDENT2, X).
