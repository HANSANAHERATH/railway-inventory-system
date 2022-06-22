default:
	$(MAKE) -C railway-service
	@ docker build -t railway-inventory-system/client .
