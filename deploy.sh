docker build --platform linux/amd64 -t $(aws sts get-caller-identity --query Account --output text).dkr.ecr.eu-west-3.amazonaws.com/kuma:latest -f Dockerfile.prod .

docker push $(aws sts get-caller-identity --query Account --output text).dkr.ecr.eu-west-3.amazonaws.com/kuma:latest