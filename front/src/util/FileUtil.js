import axios from 'axios';
import api from './APIUtils';

const fileApi = axios.create({
  responseType: 'blob',
});
const downloadFile = async (payload) => {
  console.log(payload);
  await fileApi.get(payload.url)
  .then((response) => {
    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', payload.originName);
    document.body.appendChild(link);
    link.click();
  });
};

export { downloadFile };
