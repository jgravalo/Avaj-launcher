MAIN = com.jgravalo.avaj.simulator.Simulator
SCENARIO ?= scenario.txt

# "make test file.txt": use the word after "test" as the scenario
# and turn it into a no-op target so make does not try to build it
ifeq (test,$(firstword $(MAKECMDGOALS)))
  ARG := $(word 2,$(MAKECMDGOALS))
  ifneq ($(ARG),)
    SCENARIO := $(ARG)
    $(eval .PHONY: $(ARG))
    $(eval $(ARG):;@:)
  endif
endif

all:
	find * -name "*.java" > sources.txt
	javac @sources.txt

test: all
	java $(MAIN) "$(SCENARIO)"
	cat simulation.txt

clean:
	find * -name "*.class" -delete

fclean: clean
	rm -f simulation.txt

re:	fclean all

.PHONY: all test clean fclean re
