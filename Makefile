default:
	$(MAKE) -C railway-service
	@ docker build -t railway_system/nginx:latest .
