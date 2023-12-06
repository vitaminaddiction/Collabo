<script th:inline="javascript">
        document.addEventListener('DOMContentLoaded', () => {
            let project = [[${project}]];

            let startDate = new Date(project.startLine);
            let endDate = new Date(project.deadLine);

            let currentDate = new Date();
            let totalDate = Math.floor((endDate - startDate) / (1000 * 60 * 60 * 24));
            let daysPassed = Math.floor((currentDate - startDate) / (1000 * 60 * 60 * 24));

            let percentage = ((daysPassed / totalDate) * 100).toFixed(2);

            document.getElementById('progress-bar').style.width = percentage + '%';
            document.getElementById('progress-bar-text').textContent = percentage + '%';

            let TODOList = [[${TODOList}]];
            let ongoingTODOList = [[${ongoingTODOList}]];
            let doneTODOList = [[${doneTODOList}]];

            let countTotal = [0, 0, 0, 0];
            let countDone = [0, 0, 0, 0];

            const countItem = (list, countArray) => {
                for ( item of list ){
                    switch ( item.c_idx ){
                        case "상":
                            countArray[0] += 1;
                            break;
                        case "중":
                            countArray[1] += 1;
                            break;
                        case "하":
                            countArray[2] += 1;
                            break;
                        case "기타":
                            countArray[3] += 1;
                            break;
                    }
                }
            };

            countItem(TODOList, countTotal);
            countItem(ongoingTODOList, countTotal);
            countItem(doneTODOList, countDone);

            const ratioDoneTotal = countDone.map((done, index) => (done / countTotal[index]) * 100);

            document.getElementById('progressbar-high').style.width = ratioDoneTotal[0] + '%';
            document.getElementById('progressbar-medium').style.width = ratioDoneTotal[1] + '%';
            document.getElementById('progressbar-low').style.width = ratioDoneTotal[2] + '%';
            document.getElementById('progressbar-other').style.width = ratioDoneTotal[3] + '%';

            document.getElementById('progressbar-high-text').textContent = ratioDoneTotal[0] + '%';
            document.getElementById('progressbar-medium-text').textContent = ratioDoneTotal[1] + '%';
            document.getElementById('progressbar-low-text').textContent = ratioDoneTotal[2] + '%';
            document.getElementById('progressbar-other-text').textContent = ratioDoneTotal[3] + '%';

            myPieChart.data.datasets[0].data = countTotal;
            myPieChart.update();

            const totalSum = countTotal.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
            const doneSum = countDone.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
            percentage = ((doneSum/totalSum) *100).toFixed(2);

            document.getElementById('progressbar-cur').style.width = percentage + '%';
            document.getElementById('progressbar-cur-text').textContent = percentage + '%';
        });
    </script>