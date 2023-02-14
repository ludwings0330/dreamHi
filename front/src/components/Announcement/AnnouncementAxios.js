import jwtApi from 'util/JwtApi';

const writeAnnouncement = async (payload, success, fail) => {
  await jwtApi.post('/api/announcements/', payload).then(success).catch(fail);
};

export { writeAnnouncement };

const SearchAnnouncement = async (payload, success, fail) => {
  await jwtApi.get('/api/announcements/', { params: { payload } }).then(success).catch(fail);
};

export { SearchAnnouncement };
