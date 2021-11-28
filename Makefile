default:
	#$(MAKE) -C railway-service
	@ sudo docker build -t railway_system/nginx:latest .
