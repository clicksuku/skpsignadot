# Build stage
FROM node:20 AS build
WORKDIR /app
COPY package*.json .
RUN npm ci && npm cache clean --force
COPY . .
RUN npm run build

# Runtime stage
FROM nginx:latest AS runtime
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 3000
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:3000 || exit 1
CMD ["nginx", "-g", "daemon off;"]
