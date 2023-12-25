# Use the base image rabbitmq:3.12-management
FROM rabbitmq:3.12-management

# Enable the plugin rabbitmq_stomp
RUN rabbitmq-plugins enable --offline rabbitmq_stomp

EXPOSE 5672
EXPOSE 15672

# Expose the additional port 61613 for STOMP
EXPOSE 61613
