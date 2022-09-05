# 가동중인 awsstudy 도커 중단 및 삭제
sudo docker ps -a -q --filter "name=startapp" | grep -q . && docker stop startapp && docker rm startapp | true

echo "[AWS LOGIN]===--"
# aws cli login
aws ecr get-login-password --region ap-northeast-2 |
 docker login --username AWS --password-stdin 320263837017.dkr.ecr.ap-northeast-2.amazonaws.com

TAG=$(aws ecr describe-images --output json --repository-name startapp-image --query 'sort_by(imageDetails,& imagePushedAt)[-1].imageTags[0]' | jq . --raw-output)

# 도커허브 이미지 pull
echo "[AWS IMAGE PULL] --------"
docker pull 320263837017.dkr.ecr.ap-northeast-2.amazonaws.com/startapp-image:$TAG

# 도커 run
echo "[DOCKER RUN]-----------"
docker run -d -p 8080:8080  -e PROFILE=dev --network startappnet --name startapp 320263837017.dkr.ecr.ap-northeast-2.amazonaws.com/startapp-image:$TAG

# 사용하지 않는 불필요한 이미지 삭제 -> 현재 컨테이너가 물고 있는 이미지는 삭제 안됨
echo "[DOCKER CLEAN UP]"
docker rmi -f $(docker images -f "dangling=true" -q) || true