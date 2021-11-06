default:
	$(MAKE) -C client
	@ docker build -t team_track/nginx:latest .
